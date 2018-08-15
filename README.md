# Property Based Testing

## What?

### Terms
**Domain**: set of all possible inputs for a given function

**Range**: set of all possible outputs for a given function

### Our Job as Engineers
- Limit the domain and range of our functions to tractably testable sizes
- Test as much of that domain and range as feasible, approaching exhaustive coverage

### Aside

Whenever `String` is a permissible input, the entire works of Shakespeare in Korean concatenated with that of Japanese is a permissible input.

### Call to Action

Our first mission is to try our best to define functions with such limited domain and range that they are exhaustibly testable in example-based scenarios.
Unfortunately, "reality" often dictates a domain and range that cannot reasonably be exhaustibly tested (although I think this "reality" does not *need* to exist as frequently as we employ it, but that's a story for another day).
This is where property-based testing comes to our aid.

### Property Based Testing
A means of asserting "properties" of our functions that should hold under anything permissible in the domain and range of our function.
Property based testing seeks to find falsifying examples of our asserted properties via random, generative inputs.


## Why?

Unit testing is not enough. You can have 100% line and 100% branch coverage and still have bugs.
We need something more. Property-based testing can help (but it is not a panacea: see [Dependent] Static Types (Haskell, Idris, Agda), formal methods (TLA+)).

- Curry-Howard Isomorphism
- Gödel's Incompleteness Theorems


## How?

- Assert a "property" about a given function
- Instruct your property testing framework to generate `n` samples in the specified domain
- Hope for a falsifying case to show you the error of your ways

Live coding demo! 


## Angst Remains
Property based testing can only provide proofs of contradiction in the form of falsifying examples.
Satisfied properties that aren't exhaustively tested in the domain and range of a function are not satisfied with certainty.
If correctness is your desire, constrain your domain (and range, but that doesn't rhyme)!
This is why I'm obsessed with static types and other forms of static analysis, but that's a story for another day...


## Property Based Testing Frameworks

- Java
  - [vavr-test](http://www.vavr.io/vavr-docs/#_property_checking)
  - [junit-quickcheck](https://github.com/pholser/junit-quickcheck)
  - [QuickTheories](https://github.com/ncredinburgh/QuickTheories)
  
- Python
  - [hypothesis](https://hypothesis.readthedocs.io/en/latest/)
  
- Javascript
  - [jsverify](https://github.com/jsverify/jsverify)
  
- If you're using some other hipster language you can find your own property based testing framework


## Honestly
Property based testing is hard, and takes a lot of time.
Our finite effort is probably better spent limiting our domain and range of our functions wherever possible.
But in the presence of large domains and ranges combined with the need for extensive verification, property based testing can be quite helpful.
