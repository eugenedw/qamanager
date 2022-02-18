import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'abstractfilter',
    pure: false
})
export class AbstractFilterPipe implements PipeTransform {
  transform(items: Array<any>, field: string, filter: string): Array<any> {
    if (!items || !filter || !field) {
      return items;
    }
    // filter items array, items which match and return true will be kept, false will be filtered out
    return items.filter((item: any) => this.applyFilter(item, field, filter));
  }
  
  /**
   * Perform the filtering.
   * 
   */
  applyFilter(item: any, field: string, filter: string): boolean {
    let _fields = field.split(".");
    let _item = item;
    for( let f in _fields ){
      _item = _item[_fields[f]]
    }
    if( typeof _item === 'string' ){
      if (_item.toLowerCase().indexOf(filter.toLowerCase()) === -1) {
        return false;
      }
    }
    return true;
  }
}