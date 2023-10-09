export class Todo {
  id?: number;  // '?' denotes it's an optional field, as when creating a new Todo it won't have an id yet.
  summary: string;
  description: string;

  // Optionally, you can add a constructor to initialize values if needed.
  constructor(summary?: string, description?: string) {
    this.summary = summary || '';
    this.description = description || '';
  }
}


