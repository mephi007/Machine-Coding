package models;

public class User {
    private String id;
    private String name;

    public User(String id, String name){
        this.name= name;
        this.id = id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
