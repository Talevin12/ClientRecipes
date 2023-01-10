package com.example.foodizclient.callbacks;

import com.example.foodizclient.boundaries.ObjectBoundary;

import java.util.ArrayList;

public interface DataCallback {
    void setObjectBoundary(ObjectBoundary object);

    void setListOfObjects(ArrayList<ObjectBoundary> objects);

    void setObject(Object object);
}
