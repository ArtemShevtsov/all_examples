import Ember from 'ember';

export default Ember.Component.extend({
  classNames: ['list-filter'],
  value: '',

  init(){
    this._super(...arguments);
    this.get('filter')('').then((results) => this.set('results', results));
  },

  actions: {
    handleFilterEntry(){
      let filterInputVal = this.get('value');
      let filterAction = this.get('filter');
      filterAction(filterInputVal).then((filteredResults) => this.set('results', filteredResults));

    }
  }
});
