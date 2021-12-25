package org.example.model;

import org.example.Constants;

/**
 * Класс для сохранения истории об изменениях в MongoDB
 */
public class HistoryContent {
    String className;
    String createdDate;
    String actor = Constants.ACTOR;
    public enum Status {SUCCESS, FAULT};
    Status status;
    String stringEntity;

    /**
     * Конструктор для создания истории
     * @param className Имя класса в котором был вызван объекст
     * @param createdDate Дата изменения
     * @param status Статус изменения: Удачно или Неудачно
     * @param stringEntity Наименование класса, который будет передан в историю
     */
    public HistoryContent(String className, String createdDate, Status status, String stringEntity) {
        this.className = className;
        this.createdDate = createdDate;
        this.status = status;
        this.stringEntity = stringEntity;
    }

    public HistoryContent(String className, String createdDate, String actor, Status status, String stringEntity) {
        this.className = className;
        this.createdDate = createdDate;
        this.actor = actor;
        this.status = status;
        this.stringEntity = stringEntity;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStringEntity() {
        return stringEntity;
    }

    public void setStringEntity(String stringEntity) {
        this.stringEntity = stringEntity;
    }

    @Override
    public String toString() {
        return "HistoryContent{" +
                "className='" + className + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", actor='" + actor + '\'' +
                ", status=" + status +
                ", jsonEntity='" + stringEntity + '\'' +
                '}';
    }
}
