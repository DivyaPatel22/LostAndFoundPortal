package com.lostfound.model;

public class FoundItem {
    private int id;
    private String itemName;
    private String description;
    private String location;
    private String dateFound;
    private int userId;
    private String ownerEmail; // Add ownerEmail to store the owner's email address
    private String image; // Add image field to store the image file name
    private String username;
    private String imagePath;
    // Getters and Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
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

    public String getDateFound() { 
        return dateFound; 
    }
    public void setDateFound(String dateFound) { 
        this.dateFound = dateFound; 
    }

    public int getUserId() { 
        return userId; 
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    // New getter and setter for ownerEmail
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    // New getter and setter for image file name
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

public String getImagePath() {
    return imagePath;
}

public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
}

}
