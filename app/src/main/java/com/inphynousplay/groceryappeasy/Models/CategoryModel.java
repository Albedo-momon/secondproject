package com.inphynousplay.groceryappeasy.Models;



    public class CategoryModel {

        private String carname;
        private String ImageUrl;

        public CategoryModel(String carname, String imageUrl) {
            this.carname = carname;
            ImageUrl = imageUrl;
        }

        public CategoryModel() {
        }

        public String getCarname() {
            return carname;
        }

        public void setCarname(String carname) {
            this.carname = carname;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }
    }

