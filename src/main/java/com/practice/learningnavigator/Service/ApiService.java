package com.practice.learningnavigator.Service;

import com.practice.learningnavigator.Exceptions.NotAIntegerException;

public interface ApiService {
    String callExternalApi(String url, String number) throws NotAIntegerException;
}
