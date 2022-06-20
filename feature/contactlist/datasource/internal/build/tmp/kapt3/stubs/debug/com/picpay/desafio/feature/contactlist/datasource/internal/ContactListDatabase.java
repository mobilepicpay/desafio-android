package com.picpay.desafio.feature.contactlist.datasource.internal;

import java.lang.System;

@androidx.room.Database(entities = {com.picpay.desafio.feature.contactlist.datasource.internal.UserEntity.class}, version = 1)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/picpay/desafio/feature/contactlist/datasource/internal/ContactListDatabase;", "Landroidx/room/RoomDatabase;", "()V", "userDao", "Lcom/picpay/desafio/feature/contactlist/datasource/internal/UserDao;", "Companion", "internal_debug"})
public abstract class ContactListDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.picpay.desafio.feature.contactlist.datasource.internal.ContactListDatabase.Companion Companion = null;
    
    public ContactListDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.picpay.desafio.feature.contactlist.datasource.internal.UserDao userDao();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/picpay/desafio/feature/contactlist/datasource/internal/ContactListDatabase$Companion;", "", "()V", "createDatabase", "Lcom/picpay/desafio/feature/contactlist/datasource/internal/ContactListDatabase;", "context", "Landroid/content/Context;", "internal_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.picpay.desafio.feature.contactlist.datasource.internal.ContactListDatabase createDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}