# lein-fortune

A simple webapp, to show fortune cookies. 
You can use it in manual or random mode(via config).


## Usage
<code>lein.sh clean</code>

<code>lein.sh deps</code>

<code>lein.sh compile</code>

<code>lein.sh ring uberjar</code>

To start the generated artefact use either:

<code>java -jar target/lein-fortune-[version]-standalone.jar</code>

or

<code>lein.sh ring server</code>

Then go to

[http://localhost:3000/fortune](http://localhost:3000/fortune)

## License

Distributed under the Eclipse Public License, the same as Clojure.
