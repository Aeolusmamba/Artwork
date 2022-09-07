package com.aeo.artwork.tools;

import java.util.ArrayList;

public class TypeCaster {
    public static <T> ArrayList<T> castList(Object obj, Class<T> clazz)
    {
        ArrayList<T> result = new ArrayList<T>();
        if(obj instanceof ArrayList<?>)
        {
            for (Object o : (ArrayList<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}
