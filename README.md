<h1>
Automatic Fish Feeder
</h1>
<ul>
<li>
SpringBoot Application that schedules the process to auto feed the fishes in an aquarium, using Raspberry Pi 1 B+ as a microcontroller and a servo motor.
</li>
<li> The process is automated to feed the fishes 2 times in a day.
</li>
<li>The feed status can be tracked, using the rest endpoints, and also manual feeding can be done, in case the auto feed is skipped due to power failure or some other issues.</li>
<li>The python scripts makes use of the GPIO pins on RPi to control the servo motors.</li>
</ul>