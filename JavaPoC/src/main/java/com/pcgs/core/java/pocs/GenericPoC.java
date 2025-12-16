package com.pcgs.core.java.pocs;

import java.util.ArrayList;
import java.util.List;

public class GenericPoC {
    public static <T> List<T> factory() { return new ArrayList<T>(); }
}
