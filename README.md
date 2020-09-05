# QuizApp

In this Application, you will develop a Trivia quiz. This project is composed of three
Activities, which include: Main, Trivia, and Stats Activities.

## Part 1: Main Activity
The Main activity is responsible for the loading of the trivia contents (questions and
answers).  
• When the activity is created it should retrieve and parse the trivia questions.  
• The “Start Trivia” button should be disabled while the loading and parsing are being
performed.  
• After the loading and parsing are completed. Note that the “Start Trivia” button is enabled.  
• The parsing should generate a list of questions. Clicking the “Start Trivia” button
should start the Trivia activity. Clicking the “Exit” button should exit the application.

## Part 2: Trivia Activity  
• When the Trivia activity is instantiated by the Main activity it will receive a list of the
trivia questions.  
• The activity shows the question number, a countdown timer, a question text, and the
set of answer options.  
• The Trivia activity shows an image if one exists for the current question. If the current
question has an image, then the image should be downloaded from the specified
image URL indicated.  
• Your activity should ensure that the downloaded image is displayed only when it’s
question is the currently displayed question and not when other questions are
displayed.  
• While the question image is still loading you should display a progress bar indicating
the image is loading.  
• When the user answers a question, you should detect whether the selected answer
was correct or not, and keep track the number of correctly answered questions. Then
you should update the Trivia activity to display the next question.  
• We do not separate activity for each question, but instead update the layout of the
Trivia activity to show the new question. The choices should be displayed using a ListView/RecyclerView. 
Note that the number of choices for each question varies, so the views representing the choices will be 
dynamically generated in your code.  
• The countdown timer should start once the user starts the first question. If the
countdown timer reaches 0 before user answers all the questions, it will be
assumed that the remaining questions were answered incorrectly, then the user
should be sent directly to the “Stats” activity. If the user manages to answer all the
questions within the allotted time (2 minutes), then upon clicking next the user should
be sent to Stats activity.  
## Part 3: Stats Activity  
This activity shows the user the percentage correctly answered trivia questions. Clicking the “Quit” button should send the user 
to the Main activity.Clicking the “Try Again” button should send the user back to the Trivia activity and should redisplay 
the first trivia question to enable the user to retry the trivia from the first question.
