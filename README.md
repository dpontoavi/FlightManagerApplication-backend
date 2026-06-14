# Flight Management Web Application using Kotlin Backend and React

If you are a Flight Sim player and need a step more to realism here is Flight Management Web Application: A solution
for taking your airline simulation to a next level

### Sumary

- [Features](#features)
- [How to download](#download)
- How to use the Application
- Screenshots
- How to make your customizations
- [Concerns !!MUST READ!!](#concerns)

## Story behind this project

I started this project as an aid to my studies sessions using flight simulation as timer, I was surprised on how
flying would help me study, even when I'm at the cockpit actively flying the plane.</p>
So, in my free time I worked on a system that could let me plan my sessions just like a real airline plans flights.
I soon noticed that this could be more than just a study gimmick, that's why we are here now, feel free to use as you like.

# <a id="features">Features</a>

### You can host this application so that you and your friends can see and participate in flights
^^^ Please read the Concerns section to further disclaimers on this
### Admin Panel to create, edit and cancel flights
### Flight history
### Boarding Pass generator
### Simbrief Integration
### Easy to modify images and labels

# <a id="download">How to download</a>

# <a id="concerns">Concerns !!PLEASE READ!!</a>

I would like to address some concerns regarding the security of this application. As a developer,
I recognize that I do not have a formal background or deep expertise in cybersecurity. Because of this,
I am aware that there may be vulnerabilities or gaps in the current implementation that I am not fully equipped
to identify or mitigate at this stage.

That said, I am committed to continuous improvement and have strong intentions to evolve this project into a more
secure and reliable tool over time. Security is not being ignored—it is simply an area that will require further study,
external input, and iterative development to reach a higher standard.

It is also important to emphasize that any cloud-based application inherently carries additional security considerations.
Hosting systems in the cloud increases exposure and can introduce potential vulnerabilities if not properly managed. For this reason,
security should always be treated as a critical aspect of development, especially when deploying applications in cloud environments.

Although it is completely safe to run locally and using video call app (ie. Discord) to stream your screen to your friends and/or using the boarding pass generator

{
"flightNumber": "LA1234",
"origin": {
"IATA": "FOR",
"ICAO": "SBFZ",
"NAME": "Pinto Martins",
"CITY": "Fortaleza"
},
"destination": {
"IATA": "BSB",
"ICAO": "SBBR",
"NAME": "Juscelino Kubitzchek",
"CITY": "Brasilia"
},
"aircraft": {
"model": "A320",
"regNumber": "PR-AVI",
"airline": "LATAM Airlines",
"manufacturer": "AIRBUS"
},
"passenger": "Carolina Olv",
"terminal": "1",
"gate": "14",
"group": 2,
"seat": "12A",
"flightDepart": "17:00",
"flightArr": "19:40",
"date": "2026-04-15",
"status": "SCHEDULED"
}