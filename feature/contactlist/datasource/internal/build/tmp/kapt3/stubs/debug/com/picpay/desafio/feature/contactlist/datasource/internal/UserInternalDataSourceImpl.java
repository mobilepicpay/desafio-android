package com.picpay.desafio.feature.contactlist.datasource.internal;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserInternalDataSourceImpl;", "Lcom/picpay/desafio/feature/contactlist/repository/UserInternalDataSource;", "userDao", "Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserDao;", "userMapper", "Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserEntityMapperWithDomain;", "(Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserDao;Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserEntityMapperWithDomain;)V", "getUsers", "", "Lcom/picpay/desafio/feature/contactlist/usecase/UserDomain;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUsers", "", "users", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "internal_debug"})
public final class UserInternalDataSourceImpl implements com.picpay.desafio.feature.contactlist.repository.UserInternalDataSource {
    private final com.picpay.desafio.feature.contactlist.datasource.internal.UserDao userDao = null;
    private final com.picpay.desafio.feature.contactlist.datasource.internal.UserEntityMapperWithDomain userMapper = null;
    
    public UserInternalDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.picpay.desafio.feature.contactlist.datasource.internal.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.picpay.desafio.feature.contactlist.datasource.internal.UserEntityMapperWithDomain userMapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.picpay.desafio.feature.contactlist.usecase.UserDomain>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insertUsers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.picpay.desafio.feature.contactlist.usecase.UserDomain> users, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}