import { Component } from '@angular/core';
import { ChatbotComponent } from './chatbot/chatbot.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  imports: [ChatbotComponent],
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Chatbot App';
}
