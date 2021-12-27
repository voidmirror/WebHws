import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Recipe } from './recipe';
import { Observable } from 'rxjs';
import { IngredService } from './ingred.service';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipeList!: Recipe[]
  selectedRecipe: number = -1;
  isSelected: Boolean = false;

  constructor(private http: HttpClient) {
    this.fillRecipeList();
    
    
   }

  isRecipeListEmpty() {
    // later
  }

  public retrieveAllRecipies(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>('https://localhost:8443/rest/v1/recipe');
  }

  public fillRecipeList() {
    this.retrieveAllRecipies().subscribe(
      data => {
        console.log(data);
        
        this.recipeList = data as Recipe[];
        console.log(this.recipeList[0].ingredList[0]);
        console.log(typeof this.recipeList);
        
      }
    )
  }

  findRecipe(id: number): Recipe {
    for (let i = 0; i < this.recipeList.length; i++) {
      if (this.recipeList[i].id == id) {
        return this.recipeList[i];
      }
    }
    return this.recipeList[0];
  }

  enableSelected() {
    this.isSelected = true;
  }

}
