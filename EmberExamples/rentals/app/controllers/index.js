import Ember from 'ember';

export default Ember.Controller.extend({
  actions:{
    filterByName(name){
      if(name !== ''){
        return this.get('store').query('rental', {name: name});
      } else {
        return this.get('store').findAll('rental');
      }
    }
  }
});
