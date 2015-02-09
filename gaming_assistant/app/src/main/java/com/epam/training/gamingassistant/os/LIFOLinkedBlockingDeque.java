package com.epam.training.gamingassistant.os;

import java.util.concurrent.LinkedBlockingDeque;


public class LIFOLinkedBlockingDeque<T> extends LinkedBlockingDeque<T> {
    private static final long serialVersionUID = -4114786347960826192L;

    @Override
    public boolean offer(T e) {
        return super.offerFirst(e);
    }

    @Override
    public T remove() {
        return super.removeFirst();
    }
}
