import { test } from 'qunit';
import moduleForAcceptance from 'rentals/tests/helpers/module-for-acceptance';

moduleForAcceptance('Acceptance | homepage');

test('should visit / page', function(assert){
  visit('/');
  andThen(function(){
    assert.equal(currentURL(), '/');
  });
});

test('should list available rentals', function(assert){
  visit('/');
  andThen(function () {
    assert.equal(this.$('.listing').length, 3, "should see 3 listings");
  });
});

test('should link to information about the company', function(assert){
  visit('/');
  click('a#about');
  andThen(function(){
    assert.equal(currentURL(), '/about', 'a#about should navigate to about page');
  });
});

test('should link to contact information', function(assert){
  visit('/');
  click('a#contact');
  andThen(function(){
    assert.equal(currentURL(), '/contact', 'a#contact should navigate to contact page');
  });
});

// test('should filter the list of rentals by city', function(assert){
//   visit('/');
//   fillIn('input#list-filter', 'norris');
//   keyEvent('input#list-filter', 'keyup', 13);//Enter keyup
//   andThen(function(){
//     assert.equal(this.$('.listing').length, 1, "should show just 1 listing");
//     assert.equal(this.$('.listing .name:contains(\'Chuck Norris\')').length, 1, "should show just 1 listing with name Chuck Norris");
//   });
// });
