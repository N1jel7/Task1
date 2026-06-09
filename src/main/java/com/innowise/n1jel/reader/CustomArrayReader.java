package com.innowise.n1jel.reader;

import com.innowise.n1jel.exception.CustomArrayException;

import java.util.List;

public interface CustomArrayReader {

    List<String> readAllLinesFromFile(String path) throws CustomArrayException;
}
