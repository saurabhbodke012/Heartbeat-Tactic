# Fault Detection: Heartbeat tactic

## What is Heartbeat tactic?
- To allow processes to monitor availability of a critical component.
- The HeartbeatSender sends a heartbeat message periodically.
- The HeartbeatReceiver receives the heartbeat, updates the last received time of a heartbeat message is updated.

## Objective:
Implement the “Heartbeat” Tactic for improving the availability of a self-driving (autonomous) car. The implementation is minimum prototyping of the tactic than full implementation of a self-driving car.

### The implementation has following criteria/Main Features:
- Develop a critical process (with minimum functionality)
- Design a Non-deterministic failure in this process which makes it crash.
- Implement Heartbeat to monitor the process
- Your heartbeat implementation should have all the required fault detection features.

### Cases to follow:
- Rule 1: Do not embed a failure in a static if statement. The failure must be random and it must cause the process crash, avoid making the process sleep.
- Rule 2: Implement send/receive/monitoring functions on different processes 
- Rule 3: Select a relevant domain: Monitoring connections, Monitoring process, … 
- Rule 4: Use inter-process communication mechanisms and remote method invocations; solutions based on networking protocols (e.g. UPD etc.) are not considered for this assignment.

## Modules
- Faulthandler: Handling the error in log file
- HeartbeatMonitor: Analyses the heartbeat messages of the sender and send exception to Faulthandler
  - Receiver
  - ReceiverInterface
- logger: To log the error in file
- Sender: Send the hearbeat(ping) to server
- Main: intialise the processes for receiver and sender

### Requirements
* Java version 8 or higher.

### Setup and run project
- Run the following file either in IDE(IntelIJ) or from commandline: ProcessBuilder/Main.java
