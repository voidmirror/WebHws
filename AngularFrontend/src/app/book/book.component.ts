import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IngredService } from '../ingred.service';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  constructor(public recipeService: RecipeService,
    public ingredService: IngredService,
    private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  public openPopup(content: any) {
    const modalRef = this.modalService.open(content);
  }

}
