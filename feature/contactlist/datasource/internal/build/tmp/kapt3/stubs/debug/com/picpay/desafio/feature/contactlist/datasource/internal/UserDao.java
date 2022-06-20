package com.picpay.desafio.feature.contactlist.datasource.internal;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J%\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\t\"\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserDao;", "", "getAllUser", "", "Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "", "users", "", "([Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "internal_debug"})
public abstract interface UserDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "Select * from user")
    public abstract java.lang.Object getAllUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.picpay.desafio.feature.contactlist.datasource.internal.UserEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    com.picpay.desafio.feature.contactlist.datasource.internal.UserEntity[] users, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}