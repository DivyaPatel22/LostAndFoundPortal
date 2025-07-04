package com.lostfound.model;


public class LostItem {
    private int id;
    private String itemName;
    private String description;
    private String location;
    private String dateLost;
    private int userId;
    private String ownerEmail;
    private String username; // 👈 Added to store the username

    // Getters and Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getItemName() { 
        return itemName; 
    }
    public void setItemName(String itemName) { 
        this.itemName = itemName; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getLocation() { 
        return location; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }

    public String getDateLost() { 
        return dateLost; 
    }
    public void setDateLost(String dateLost) { 
        this.dateLost = dateLost; 
    }
    

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}


    public int getUserId() { 
        return userId; 
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

  
}
