import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('actor-listing', 'Integration | Component | actor listing', {
  integration: true
});

test('Should toogle wide class of .image on click', function(assert) {
  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  let stubActor = Ember.Object.create({
    id: 0,
    type: 'test-type',
    name: 'test-name',
    age: 30,
    image: 'test.png'
  });
  this.set('actorObj', stubActor);
  this.render(hbs`{{actor-listing actor=actorObj}}`);

  assert.equal(this.$('.image.wide').length, 0, 'Initially load small image');
  this.$('.image').click();
  assert.equal(this.$('.image.wide').length, 1, 'After Click image becomes wide (with wide class)');
  this.$('.image').click();
  assert.equal(this.$('.image.wide').length, 0, 'After Thecond click image again small');

});
