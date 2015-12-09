package com.gofriend.vienngan.vngofriend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tmvien on 12/9/15.
 */
public class InternalDataPipe {
    private static List<Registration<?>> sRegistrations = new ArrayList<Registration<?>>();

    public static synchronized <T extends InternalData> Registration<T> register(
            Object tag,
            Class<T> dataClass,
            DataServer<T> dataServer) {
        Registration<T> registration = new Registration<T>(tag, dataClass, dataServer);
        sRegistrations.add(registration);
        return registration;
    }

    private static synchronized void unregister(Registration<?> registration) {
        sRegistrations.remove(registration);
    }

    public static synchronized void unregister(Object tag) {
        for (Iterator<Registration<?>> iter = sRegistrations.iterator(); iter.hasNext(); ) {
            Registration<?> registration = iter.next();
            if (registration.mTag == tag) {
                iter.remove();
            }
        }
    }

    public static synchronized <T extends InternalData> List<T> getData(Class<T> dataClass) {
        List<T> dataList = new ArrayList<T>();
        for (Registration<?> registration : sRegistrations) {
            if (dataClass.isAssignableFrom(registration.mDataClass)) {
                dataList.add(((DataServer<T>) registration.mDataServer).getData());
            }
        }
        return dataList;
    }

    public static class Registration<T extends InternalData> {

        private final Object mTag;

        private final Class<T> mDataClass;

        private final DataServer<T> mDataServer;


        public Registration(Object tag, Class<T> dataClass, DataServer<T> dataServer) {
            mTag = tag;
            mDataClass = dataClass;
            mDataServer = dataServer;
        }

        public void cancel() {
            unregister(this);
        }

    }

    public interface DataServer<T extends InternalData> {

        T getData();

    }

    public interface InternalData {

    }
}
