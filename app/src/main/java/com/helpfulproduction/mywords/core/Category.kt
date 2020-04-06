package com.helpfulproduction.mywords.core

import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import com.helpfulproduction.mywords.R

open class Category(
    @CategoryIds val id: Int,
    val title: String,
    var isSelected: Boolean
) {

    @DrawableRes
    fun getIcon(): Int {
        return when (id) {
            EDUCATION -> R.drawable.ic_education_40
            FAMILY -> R.drawable.ic_family_40
            LEISURE -> R.drawable.ic_leisure_40
            BODY -> R.drawable.ic_body_40
            EMOTIONS -> R.drawable.ic_emotions_40
            SPORT -> R.drawable.ic_sport_40
            CLOTHES -> R.drawable.ic_clothes_40
            FURNITURE -> R.drawable.ic_furniture_40
            RELATIONSHIP -> R.drawable.ic_relationship_40
            MONEY -> R.drawable.ic_money_40
            HOBBY -> R.drawable.ic_hobby_40
            ANIMALS -> R.drawable.ic_animals_40
            HOUSE -> R.drawable.ic_house_40
            MOVEMENT -> R.drawable.ic_movement_40
            FRUITS -> R.drawable.ic_fruits_40
            TRANSPORT -> R.drawable.ic_transport_40
            KITCHENWARE -> R.drawable.ic_kitchenware_40
            WEATHER -> R.drawable.ic_weather_40
            CITY -> R.drawable.ic_city_40
            VEGETABLES -> R.drawable.ic_vegetables_40
            CHARACTER -> R.drawable.ic_character_40
            APPEARANCE -> R.drawable.ic_appearance_40
            HEALTH -> R.drawable.ic_health_40
            FOOD -> R.drawable.ic_food_40
            DRINKS -> R.drawable.ic_drink_40
            COLORS -> R.drawable.ic_colors_40
            PROFESSIONS -> R.drawable.ic_professions_40
            FISH -> R.drawable.ic_fish_40
            SHOES -> R.drawable.ic_shoes_40
            else -> throw UnsupportedOperationException("This type $id is unsupported yet")
        }
    }

    @IntDef(
        EDUCATION,
        FAMILY,
        LEISURE,
        BODY,
        EMOTIONS,
        SPORT,
        CLOTHES,
        FURNITURE,
        RELATIONSHIP,
        MONEY,
        HOBBY,
        ANIMALS,
        HOUSE,
        MOVEMENT,
        FRUITS,
        TRANSPORT,
        KITCHENWARE,
        WEATHER,
        CITY,
        VEGETABLES,
        CHARACTER,
        APPEARANCE,
        HEALTH,
        FOOD,
        DRINKS,
        COLORS,
        PROFESSIONS,
        FISH,
        SHOES
    )
    annotation class CategoryIds

    companion object {
        const val EDUCATION = 0
        const val FAMILY = 1
        const val LEISURE = 2
        const val BODY = 3
        const val EMOTIONS = 4
        const val SPORT = 5
        const val CLOTHES = 6
        const val FURNITURE = 7
        const val RELATIONSHIP = 8
        const val MONEY = 9
        const val HOBBY = 10
        const val ANIMALS = 11
        const val HOUSE = 12
        const val MOVEMENT = 13
        const val FRUITS = 14
        const val TRANSPORT = 15
        const val KITCHENWARE = 16
        const val WEATHER = 17
        const val CITY = 18
        const val VEGETABLES = 19
        const val CHARACTER = 20
        const val APPEARANCE = 21
        const val HEALTH = 22
        const val FOOD = 23
        const val DRINKS = 24
        const val COLORS = 25
        const val PROFESSIONS = 26
        const val FISH = 27
        const val SHOES = 28
    }
}