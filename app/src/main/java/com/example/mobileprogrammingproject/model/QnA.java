package com.example.mobileprogrammingproject.model;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
@Getter
@Setter
@ToString
public class QnA {

    // Attributes
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String content;
    private String creationDate;
    private String title;

    public QnA(int userId, String content, String creationDate, String title){
          this.userId = userId;
          this.content = content;
          this.creationDate = creationDate;
          this.title = title;
    }

    }

