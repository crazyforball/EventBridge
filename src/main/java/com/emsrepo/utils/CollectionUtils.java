package com.emsrepo.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {

	 /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty() || collection.size() <=0);
    }

    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty() || map.size() <= 0);
    }

    /**
     * Return {@code true} if the supplied Collection is neither {@code null} nor empty.
     * Otherwise, return {@code false}.
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isNotEmpty(Collection<?> collection) { return !isEmpty(collection); }

    /**
     * Return {@code true} if the supplied Map is neither {@code null} nor empty.
     * Otherwise, return {@code false}.
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isNotEmpty(Map<?, ?> map) { return !isEmpty(map); }
}
