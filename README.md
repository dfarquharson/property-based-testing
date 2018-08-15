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
Unfortunately, "reality" often dictates a domain and range that cannot reasonably be exhaustibly tested
(although I think this "reality" does not *need* to exist as frequently as we employ it, but that's a story for another day).
This is where property-based testing comes to our aid.

### Property Based Testing
A means of asserting "properties" of our functions that should hold under anything permissible in the domain and range of our function.
Property based testing seeks to find falsifying examples of our asserted properties via random, generative inputs.


## Why?

Unit testing is not enough. You can have 100% line and 100% branch coverage and still have bugs.
We need something more. Property-based testing can help (but it is not a panacea: see [Dependent] Static Types (Haskell, Idris, Agda), formal methods (TLA+)).

### Name Dropping Some Fancy-Sounding Things Due to Their Implication on the Pursuit of Deterministic Certainty
- [Curry-Howard Isomorphism](https://en.wikipedia.org/wiki/Curry%E2%80%93Howard_correspondence) 
- [Gödel's Incompleteness Theorems](https://en.wikipedia.org/wiki/G%C3%B6del%27s_incompleteness_theorems)


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
  
- If you're using some other hipster language you can find your own property based testing framework, you dirty hipster


## Sober Conclusion
Property based testing is hard, makes you think, and takes a lot of time.
Our finite effort is probably better spent limiting our domain and range of our functions wherever possible.
But in the presence of large domains and ranges combined with the need for extensive verification, property based testing can be quite helpful.

We continue to chase the white rabbit of software quality and correctness, but property-based testing is a tool that gets us a little bit closer to catching it
(although we are perhaps forever doomed to some variation of [Zeno's Paradox](https://en.wikipedia.org/wiki/Zeno%27s_paradoxes) in this regard).
There are still other means we can use to get closer:
- Primarily employing [total functions](https://en.wikipedia.org/wiki/Partial_function#Total_function)
- Opting out of [Turing Completeness](https://en.wikipedia.org/wiki/Turing_completeness) to avoid pernicious proliferation of the halting problem
- [Type Theory](https://en.wikipedia.org/wiki/Type_theory) in general is a tremendously active area of research directly related to software correctness
- [Abstract Nonsense](https://en.wikipedia.org/wiki/Category_theory)
