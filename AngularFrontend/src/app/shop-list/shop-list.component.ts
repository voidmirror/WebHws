import { Component, OnInit } from '@angular/core';
import { IngredService } from '../ingred.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {

  constructor(public ingredService: IngredService) { }

  ngOnInit(): void {
  }

}
