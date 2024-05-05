[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/XpMVWSvD)
<h1>Your Name(s) Rotem Kashani and David Koplev</h1>
<p>Email: rotemkash@edu.hac.ac.il<br/>
  Email: davidkop@edu.hac.ac.il
</p>
<h2>Description:</h2>
This program designed to retrieve and process image URLs from a file and/or user input.
<ol>
<li><b> Input Handling:</b>
The program accepts command-line arguments specifying the output format, thread pool size, and input file path 
containing URLs. It reads image URLs from both a file and user input (terminal).
</li>
<li><b>Thread Pool Initialization:</b>
It creates a fixed-size thread pool using the ExecutorService to manage concurrent execution of tasks.
</li>
<li><b>URL Processing:</b>
For each image URL read from the file or user input, the program creates an ImageCrawler instance and submits it to the
thread pool for processing. The ImageCrawler class is responsible for processing individual URLs. Each ImageCrawler 
instance retrieves image data from the URL and outputs information such as content length, URL, response time, and 
content type based on the specified output format.
</li>
<li><b>Concurrency Control:</b>
The program ensures that duplicate URLs are not processed by maintaining a set of processed URLs.
Synchronization is used to prevent race conditions when updating the set of processed URLs.
</li>
<li><b>Output Formatting:</b>
The output format string determines which information is included in the output for each processed URL.
The formatOutput method in the ImageCrawler class formats the output string based on the specified parameters.
</li>
<li><b>Error Handling:</b>
The program handles various exceptions, such as malformed URLs and IO errors, and prints error messages to standard
error.
</li>
<li><b>Resource Cleanup:</b>
Proper resource management is ensured by closing file readers and shutting down the thread pool after all tasks are 
completed.
</li>
</ol>
<b>Note:</b> The program accepts URLs from image type only and not from other types!
<p>

</p>
<h2>Design Patterns:</h2>
<ol>
<li>
<b>Observer Pattern:</b>
Although not explicitly implemented, our code demonstrates aspects of the Observer pattern.
Each ImageCrawler instance acts as an observer that monitors and processes image URLs asynchronously.
As URLs are read from both the file and terminal, the ImageCrawler instances react to these events and execute their 
tasks accordingly. This pattern helps maintain loose coupling between the input source (file or terminal) and 
the processing logic.
</li>
<li>
<b>Singleton Pattern:</b>
The HashSet processedUrls is used to keep track of processed URLs and avoid processing duplicates. While not implemented
as a traditional singleton, it serves a similar purpose by ensuring that only one instance of the set is used across all
ImageCrawler instances. This pattern helps centralize state management and prevent unnecessary duplication of processing
efforts.
</li>
</ol>
<h2>Usage:</h2>
<p>To run the program, use the following command:</p>
<pre>java Main &lt;output_format&gt; &lt;pool_size&gt; &lt;input_file&gt;</pre>
<p>Where:</p>
<ul>
  <li><code>&lt;output_format&gt;</code>: Specifies the output format string (e.g., "us").
The output format string can contain one or more of the following characters:
    <ul>
      <li><code>s</code>: Include the content length in the output</li>
      <li><code>u</code>: Include the URL in the output</li>
      <li><code>t</code>: Include the elapsed time in the output</li>
      <li><code>m</code>: Include the content type in the output</li>
    </ul>
  </li>
  <li><code>&lt;pool_size&gt;</code>: Specifies the pool size for the thread pool.</li>
  <li><code>&lt;input_file&gt;</code>: Specifies the input file path containing URLs.</li>
</ul>
<p>Example:</p>
<pre>java Main sutm 5 urls.txt</pre>
<p>This command will process the URLs listed in the "urls.txt" file, using a thread pool size of 5,
and output the results according to the "sutm" format, which includes the content length, URL, elapsed time, and content type.</p>
