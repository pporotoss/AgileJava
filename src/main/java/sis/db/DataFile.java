package sis.db;

import sis.studentinfo.Student;
import sis.util.IOUtil;

import java.io.*;

public class DataFile {
    public static final String DATA_EXT = ".db";
    public static final String KEY_EXT = ".idx";

    private String dataFileName;
    private String keyFileName;

    private RandomAccessFile db;
    private KeyFile keys;

    public static DataFile create(String filebase) throws IOException {
        return new DataFile(filebase, true);
    }

    private DataFile(String filebase, boolean deleteFiles) throws IOException {
        dataFileName = filebase + DATA_EXT;
        keyFileName = filebase + KEY_EXT;

        if(deleteFiles) {
            deleteFiles();
        }
        openFiles();
    }

    public static DataFile open(String fileBase) throws IOException {
        return new DataFile(fileBase, false);
    }

    public void add(String key, Object object) throws IOException {
        long position = db.length();

        byte[] bytes = getBytes(object);
        db.seek(position);
        db.write(bytes, 0, bytes.length);

        keys.add(key, position, bytes.length);
    }

    public Object findBy(String id) throws IOException {
        if(!keys.containsKey(id)) {
            return null;
        }

        long position = keys.getPosition(id);
        db.seek(position);

        int length = keys.getLength(id);
        return read(length);
    }

    private Object read(int length) throws IOException {
        byte[] bytes = new byte[length];
        db.readFully(bytes);
        return readObject(bytes);
    }

    private Object readObject(byte[] bytes) throws IOException {
        try(ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            try {
                return input.readObject();
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }

    private void openFiles() throws FileNotFoundException {
        keys = new KeyFile(keyFileName);
        db = new RandomAccessFile(new File(dataFileName), "rw");
    }

    private byte[] getBytes(Object object) throws IOException {
        try(ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteStream)) {

            outputStream.writeObject(object);
            outputStream.flush();
            return byteStream.toByteArray();
        }
    }


    public int size() {
        return keys.size();
    }

    public void close() throws IOException {
        keys.close();
        db.close();
    }

    public void deleteFiles() {
        IOUtil.delete(dataFileName, keyFileName);
    }
}
