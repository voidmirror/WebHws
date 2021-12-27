import { Component, Input, OnInit } from '@angular/core';
import { IngredService } from '../ingred.service';
import { Recipe } from '../recipe';
import { ShopItem } from '../shopItem';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit 
{

  @Input() recipe!: Recipe;

  // ingName!: string[]

  constructor(private ingredService: IngredService) {
    
   }

  ngOnInit(): void {
  }

  getIngredName(ingred: string): string {
    let arr = ingred.split(':');
    return arr[0];
  }

  getIngredCount(ingred: string): string {
    let arr = ingred.split(':');
    return arr[1];
  }

  addIngred() {
    for (let i = 0; i < this.recipe.ingredList.length; i++) {
      let sh = new ShopItem();
      sh.name = this.getIngredName(this.recipe.ingredList[i]);
      sh.count = parseInt(this.getIngredCount(this.recipe.ingredList[i]));
      this.ingredService.addItem(sh);
    }
  }

}
