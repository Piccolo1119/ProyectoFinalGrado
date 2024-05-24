package com.web.myapp.mapper;

@FunctionalInterface
public interface ModelMapper<I, O> {
    O map(I input);
}
