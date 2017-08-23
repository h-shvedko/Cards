package com.cards.shvedko.Model;

import javax.persistence.*;

@Entity
@Table(name = "LANGUAGES")
public class CardLanguages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "alias", unique = true, nullable = false)
    private String alias;

    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @Column(name = "table_name", unique = true, nullable = false)
    private String tableName;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguages() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguages(Integer id, String name, String alias, String label, String tableName, int isVisible) {
        this.setId(id);
        this.setName(name);
        this.setAlias(alias);
        this.setLabel(label);
        this.setTableName(tableName);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguages(String name, String alias, String label, String tableName, int isVisible) {
        this.setId(-1);
        this.setName(name);
        this.setAlias(alias);
        this.setLabel(label);
        this.setTableName(tableName);
        this.setIsVisible(isVisible);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }
}
