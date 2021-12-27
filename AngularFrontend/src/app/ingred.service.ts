import { Injectable } from '@angular/core';
import { ShopItem } from './shopItem';

@Injectable({
  providedIn: 'root'
})


export class IngredService {

  shopList!: ShopItem[];

  constructor() {
    this.shopList = [];
  }

  addItem(item: ShopItem) {
    if (this.isListEmpty()) {
      this.shopList.push(item)
    } else {
      let check = this.shopListContainsElement(item)
      if (check == null) {
        this.shopList.push(item)
      }
      else {
        
        for (let i = 0; i < this.shopList.length; i++) {
          if (this.shopList[i].name == item.name) {
            this.shopList[i].count += item.count;
          }
        }
      }
    }
    
  }

  shopListContainsElement(item: ShopItem) {
    for (let i = 0; i < this.shopList.length; i++) {
      if (this.shopList[i].name == item.name) {
        return item
      }
    }
    return null;
  }

  isListEmpty() {
    if (this.shopList.length == 0) {
      return true;
    }
    return false;
  }
}
