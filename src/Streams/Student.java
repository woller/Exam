package Streams;

public class Student {
    String name;
    String email;
    int yearStarted;
    boolean isActive;
    public Student(String name, String email, int yearStarted, boolean isActive) {
        this.name = name;
        this.email = email;
        this.yearStarted = yearStarted;
        this.isActive = isActive;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getYearStarted()
    {
        return yearStarted;
    }

    public void setYearStarted(int yearStarted)
    {
        this.yearStarted = yearStarted;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Email: " + email + ", Started: " + yearStarted + ", Active: " + isActive;
    }
}