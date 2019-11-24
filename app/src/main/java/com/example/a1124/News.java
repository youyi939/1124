package com.example.a1124;

public class News {
    private String tittle;
    private String message;
    private String image;


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "News{" +
                "tittle='" + tittle + '\'' +
                ", message='" + message + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
