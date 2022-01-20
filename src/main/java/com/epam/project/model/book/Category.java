package com.epam.project.model.book;

public enum Category {
    SCI_FI("Sci-Fi"), DETECTIVE("Detective"), NOVEL("Novel");

    private final String name;

    Category(String category) {
        this.name = category;
    }

    public String getName() {
        return name;
    }
}
