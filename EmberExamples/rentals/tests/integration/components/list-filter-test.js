import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';
import wait from 'ember-test-helpers/wait';

moduleForComponent('list-filter', 'Integration | Component | list filter', {
  integration: true
});

const ITEMS = [{name: "Jackson"}, {name: "Norris"}, {name: "Pupkin"}];
const FILTERED_ITEMS = [{name: "Norris"}];

test('Should initially load all items', function(assert) {
  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.on('filterByName', function(val){
    if(val === ''){
      return Ember.RSVP.resolve(ITEMS);
    } else {
      return Ember.RSVP.resolve(FILTERED_ITEMS);
    }
  });

  // Template block usage:
  this.render(hbs`
    {{#list-filter filter=(action 'filterByName') as |results|}}
      <ul>
      {{#each results as |item|}}
        <li class="actor">
          {{item.name}}
        </li>
        {{/each}}
      </ul>
    {{/list-filter}}
  `);

  // the wait function will return a promise that will wait for all promises
  // and xhr requests to resolve before running the contents of the then block.
  return wait().then(() => {
    assert.equal(this.$('.actor').length, 3);
    assert.equal(this.$('.actor').first().text().trim(), 'Jackson');
  });
});

test('Should update list results when type in filter input', function(assert) {
  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.on('filterByName', function(val){
    if(val === ''){
      return Ember.RSVP.resolve(ITEMS);
    } else {
      return Ember.RSVP.resolve(FILTERED_ITEMS);
    }
  });

  // Template block usage:
  this.render(hbs`
    {{#list-filter filter=(action 'filterByName') as |results|}}
      <ul>
        {{#each results as |item|}}
          <li class="actor">
            {{item.name}}
          </li>
        {{/each}}
      </ul>
    {{/list-filter}}
  `);

  this.$('.list-filter input').val('Nor').keyup();
  // the wait function will return a promise that will wait for all promises
  // and xhr requests to resolve before running the contents of the then block.
  return wait().then(() => {
    assert.equal(this.$('.actor').length, 1);
    assert.equal(this.$('.actor').first().text().trim(), 'Norris');
  });
});
