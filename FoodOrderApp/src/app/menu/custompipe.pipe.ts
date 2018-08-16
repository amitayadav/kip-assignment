import { Pipe, PipeTransform } from '@angular/core';
import {Config} from './Item';

@Pipe({
  name: 'custompipe'
})
export class CustompipePipe implements PipeTransform {


  transform(collection: Config[], property: string) {
    if (!collection) {
      return null;
    }

    const groupedCollection = collection.reduce((previous, current) => {
      if (!previous[current[property]]) {
        previous[current[property]] = [current];
      } else {
        previous[current[property]].push(current);
      }

      return previous;
    }, {});

    return Object.keys(groupedCollection).map(key => ({key, value: groupedCollection[key]}));
  }
}
