package com.koreatech.core.network;

import com.google.gson.GsonBuilder;


import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

class XmlOrJsonConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Xml.class) {
                return SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())).responseBodyConverter(type, annotations, retrofit);
            }
            if (annotation.annotationType() == Json.class) {
                return GsonConverterFactory.create(new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()).responseBodyConverter(type, annotations, retrofit);
            }
        }
        return GsonConverterFactory.create(new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()).responseBodyConverter(type, annotations, retrofit);
    }
}