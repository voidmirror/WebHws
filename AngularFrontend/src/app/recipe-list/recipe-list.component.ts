import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  

  constructor(public recipeService: RecipeService) { 
    
  }

  ngOnInit(): void {
  }

  spliceDescription(decription: string): string {
    return decription.substring(0, 50) + '...'
  }

  selectRecipe(id: number) {
    this.recipeService.selectedRecipe = id;
    // console.log(this.recipeService.selectedRecipe);
    this.recipeService.enableSelected();
  }

}
