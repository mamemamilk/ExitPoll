package com.example.exitpoll.model;

import java.util.Locale;

public class Item {
    public final long _id;
    public final String number;
    public final String point;
    public final String image;

    public Item(long _id, String number,String point,String image) {
        this._id = _id;
        this.number = number;
        this.point = point;
        this.image = image;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s ",
                this.number
        );
        return msg;
    }
}
