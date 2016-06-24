import Ember from 'ember';

const propertyTypes = [
  'Condo',
  'Townhouse',
  'Appartment'
];

export function rentalPropertyTypeHelper([type]/*, hash*/) {
  if(propertyTypes.contains(type)){
    return 'Community';
  }
  return 'Standalone';
}

export default Ember.Helper.helper(rentalPropertyTypeHelper);
