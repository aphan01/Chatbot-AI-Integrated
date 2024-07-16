import { Component, OnInit, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class ChatbotComponent implements OnInit, AfterViewChecked {
  @ViewChild('chatBody') chatBody!: ElementRef;

  message: string = '';
  response: string | undefined;
  minimized: boolean = false;
  chatHistory: { message: string, isUser: boolean, buttons?: { text: string, nextStep: string }[] }[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.initializeChat();
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  initializeChat() {
    this.response = "Hi there! How can I help you today?";
    this.chatHistory.push({
      message: this.response,
      isUser: false,
      buttons: [
        { text: 'Dashboard', nextStep: 'dashboard' },
        { text: 'My Items', nextStep: 'my items' },
        { text: 'Monitor', nextStep: 'monitor' },
        { text: 'Request', nextStep: 'request' }
      ]
    });
  }

  resetChat() {
    this.chatHistory = [];
    this.initializeChat();
    this.message = '';
    this.scrollToTop();
  }

  handleButtonClick(nextStep: string) {
    if (nextStep.startsWith('http')) {
      window.open(nextStep, '_blank');
    } else {
      this.sendMessage(nextStep);
    }
  }

  sendMessage(customMessage?: string) {
    const messageToSend = customMessage || this.message.trim();
    if (messageToSend) {
      this.chatHistory.push({
        message: messageToSend,
        isUser: true
      });

      const payload = { message: messageToSend };

      this.http.post<any>('http://localhost:8080/api/chatbot/message', payload)
        .subscribe({
          next: (res: any) => {
            this.chatHistory.push({
              message: res.response,
              isUser: false,
              buttons: res.buttons ? res.buttons.map((button: any) => ({
                text: button.text,
                nextStep: button.url
              })) : []
            });
            this.scrollToBottom();
          },
          error: (err: any) => console.error('Error:', err)
        });
      this.message = '';
    }
  }

  toggleMinimize() {
    this.minimized = !this.minimized;
  }

  scrollToBottom() {
    if (this.chatBody) {
      this.chatBody.nativeElement.scrollTop = this.chatBody.nativeElement.scrollHeight;
    }
  }

  scrollToTop() {
    if (this.chatBody) {
      this.chatBody.nativeElement.scrollTop = 0;
    }
  }
}

