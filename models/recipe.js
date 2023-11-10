export class Recipe {
    #id;
    #categoryIds;
    #title;
    #affordability;
    #complexity;
    #imageUrl;
    #duration;
    #ingredients;
    #steps;
    #isGlutenFree;
    #isVegan;
    #isVegetarian;
    #isLactoseFree;

    constructor( 
        id,
        categoryIds,
        title,
        affordability,
        complexity,
        imageUrl,
        duration,
        ingredients,
        steps,
        isGlutenFree,
        isVegan,
        isVegetarian,
        isLactoseFree
        )
        {
        this.#id = id;
        this.#title = title;
        this.#categoryIds = categoryIds;
        this.#affordability = affordability;
        this.#complexity = complexity;
        this.#imageUrl = imageUrl;
        this.#duration = duration;
        this.#ingredients = ingredients;
        this.#steps = steps;
        this.#isGlutenFree = isGlutenFree;
        this.#isVegan = isVegan;
        this.#isVegetarian = isVegetarian;
        this.#isLactoseFree = isLactoseFree;
    }

    get id() {
        return this.#id;
    }
    
    get categoryIds() {
        return this.#categoryIds;
    }
    
    get title() {
        return this.#title;
    }
    
    get affordability() {
        return this.#affordability;
    }
    
    get complexity() {
        return this.#complexity;
    }
    
    get imageUrl() {
        return this.#imageUrl;
    }
    
    get duration() {
        return this.#duration;
    }
    
    get ingredients() {
        return this.#ingredients;
    }
    
    get steps() {
        return this.#steps;
    }
    
    get isGlutenFree() {
        return this.#isGlutenFree;
    }
    
    get isVegan() {
        return this.#isVegan;
    }
    
    get isVegetarian() {
        return this.#isVegetarian;
    }
    
    get isLactoseFree() {
        return this.#isLactoseFree;
    }
}