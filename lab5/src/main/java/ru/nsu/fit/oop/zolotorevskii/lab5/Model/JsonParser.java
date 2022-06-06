package ru.nsu.fit.oop.zolotorevskii.lab5.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class JsonParser {

        private JsonParser() {
        }

        public static void main(final String... args) {
            final String[] tokens = "id31.english.B1.Animals.11".split("\\.");
            if ( tokens.length != 5 ) {
                throw new IllegalArgumentException("Not 5 tokens in " + "id31.english.B1.Animals.11");
            }
            final String userId = tokens[0];
            final String category = tokens[1];
            final String subcategory = tokens[2];
            final String subsubcategory = tokens[3];
            final int score = Integer.parseInt(tokens[4]);
            final User user = new User(
                    userId,
                    Collections.singletonList(new Category(
                            category,
                            Collections.singletonList(new Subcategory(
                                    subcategory,
                                    Collections.singletonList(new Subsubcategory(subsubcategory, score))
                            ))
                    ))
            );
            final Gson gson = new GsonBuilder().create();
            System.out.println(gson.toJson(user));
        }

        private static final class User {

            @SerializedName("user-id")
            private final String userId;

            @SerializedName("categories")
            private final List<Category> categories;

            private User(final String userId, final List<Category> categories) {
                this.userId = userId;
                this.categories = categories;
            }

        }

        private static final class Category {

            @SerializedName("category")
            private final String category;

            @SerializedName("subcats")
            private final List<Subcategory> subcategories;

            private Category(final String category, final List<Subcategory> subcategories) {
                this.category = category;
                this.subcategories = subcategories;
            }

        }

        private static final class Subcategory {

            @SerializedName("subcat")
            private final String subcategory;

            @SerializedName("subsubcats")
            private final List<Subsubcategory> subsubcategories;

            private Subcategory(final String subcategory, final List<Subsubcategory> subsubcategories) {
                this.subcategory = subcategory;
                this.subsubcategories = subsubcategories;
            }

        }

        private static final class Subsubcategory {

            @SerializedName("subsubcat")
            private final String subsubcategory;

            @SerializedName("score")
            private final int score;

            private Subsubcategory(final String subsubcategory, final int score) {
                this.subsubcategory = subsubcategory;
                this.score = score;
            }

        }
}
