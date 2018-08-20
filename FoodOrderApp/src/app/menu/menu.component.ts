import {Component, OnInit} from '@angular/core';
import {OrderingServiceService} from './ordering-service.service';
import {Config} from './Item';
import {Mycart} from './cart';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  alldata: Config[];
  category: string[] = [];

  cart: Mycart[] = [];
  totalamount = 0;

  constructor(private orderingServiceService: OrderingServiceService) {
  }

  ngOnInit() {

    this.orderingServiceService.getJsonData().subscribe((data: Config[]) => {
      this.alldata = data;
      data.map((cat) => {
        const kind = cat.category;
        this.category.push(kind);
      });
      this.category = this.category.filter(function (item, i, ar) {
        return ar.indexOf(item) === i;
      });
      console.log('><><<><><><>' + data);
      console.log('========>' + JSON.stringify(data));
    });
  }

  addItem(item1: string, price1: number, quan: number) {
    const index = this.cart.findIndex(record => record.item === item1);
    if (index === -1) {
      const i = {item: item1, price: price1, quantity: quan};
      this.cart.push(i);
      this.totalamount += price1 * quan;
    } else {
      this.cart[index].quantity += 1;
      this.totalamount += 1 * this.cart[index].price;
    }
    console.log(this.totalamount);
    console.log(this.cart);
  }

  subItem(item1: string, price1: number, quan: number) {
    const index = this.cart.findIndex(record => record.item === item1);
    if (this.cart[index].quantity >= 1) {
      this.totalamount -= 1 * this.cart[index].price;
      this.cart[index].quantity--;
    }
    if (this.cart[index].quantity === 0) {
      this.cart.splice(index, 1);
    }
    console.log(this.totalamount);
    console.log(this.cart);
  }
}
